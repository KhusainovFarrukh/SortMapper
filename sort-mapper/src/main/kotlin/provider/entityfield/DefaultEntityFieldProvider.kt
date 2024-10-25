package kh.farrukh.provider.entityfield

import java.time.temporal.Temporal
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.superclasses

class DefaultEntityFieldProvider : EntityFieldProvider {

    override fun getEntityFields(entityClass: KClass<*>): Collection<String> {
        val fields = mutableSetOf<String>()
        val visitedClasses = mutableSetOf<KClass<*>>()
        handleClass(entityClass, "", fields, visitedClasses)
        return fields
    }

    private fun handleClass(
        clazz: KClass<*>,
        parentName: String,
        fields: MutableSet<String>,
        visitedClasses: MutableSet<KClass<*>>
    ) {
        extractFields(clazz, parentName, fields, visitedClasses)
        clazz.superclasses.forEach { handleClass(it, parentName, fields, visitedClasses) }
    }

    private fun extractFields(
        clazz: KClass<*>,
        parentName: String,
        propertyNames: MutableSet<String>,
        visitedClasses: MutableSet<KClass<*>>
    ) {
        if (visitedClasses.contains(clazz)) return

        visitedClasses.add(clazz)

        //todo: i think there are not only properties, but also functions (or something else)
        val fields = clazz.members

        for (field in fields) {
            val fieldName = if (parentName.isEmpty()) field.name else "$parentName.${field.name}"
            propertyNames.add(fieldName)

            //todo: need to use kotlin-reflect correctly or migrate to java-reflect
            if (field is KClass<*> && !field.isChildrenIgnored()) {
                handleClass(field, fieldName, propertyNames, visitedClasses)
            }
        }

        visitedClasses.remove(clazz)
    }

}

fun KClass<*>.isChildrenIgnored() = isCompanion ||
        isSealed ||
        this == Boolean::class ||
        this == Byte::class ||
        this == Short::class ||
        this == Int::class ||
        this == Long::class ||
        this == Float::class ||
        this == Double::class ||
        this == Char::class ||
        this == String::class ||
        isSubclassOf(Collection::class) ||
        isSubclassOf(Temporal::class) ||
        java.isEnum ||
        java.isPrimitive ||
        java.isAnnotation