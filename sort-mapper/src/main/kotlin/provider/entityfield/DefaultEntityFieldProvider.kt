package kh.farrukh.provider.entityfield

import java.time.temporal.Temporal

class DefaultEntityFieldProvider : EntityFieldProvider {

    override fun getEntityFields(entityClass: Class<*>): Collection<String> {
        val fields = mutableSetOf<String>()
        val visitedClasses = mutableSetOf<Class<*>>()
        handleClass(entityClass, "", fields, visitedClasses)
        return fields
    }

    private fun handleClass(
        clazz: Class<*>,
        parentName: String,
        fields: MutableSet<String>,
        visitedClasses: MutableSet<Class<*>>
    ) {
        extractFields(clazz, parentName, fields, visitedClasses)

        var superclass = clazz.superclass
        while (superclass != null) {
            extractFields(superclass, parentName, fields, visitedClasses)
            superclass = superclass.superclass
        }
    }

    private fun extractFields(
        clazz: Class<*>,
        parentName: String,
        propertyNames: MutableSet<String>,
        visitedClasses: MutableSet<Class<*>>
    ) {
        if (visitedClasses.contains(clazz)) return

        visitedClasses.add(clazz)

        val fields = clazz.declaredFields

        for (field in fields) {
            val fieldName = if (parentName.isEmpty()) field.name else "$parentName.${field.name}"
            propertyNames.add(fieldName)

            if (!field.type.isChildrenIgnored()) {
                handleClass(field.type, fieldName, propertyNames, visitedClasses)
            }
        }

        visitedClasses.remove(clazz)
    }

}

fun Class<*>.isChildrenIgnored() = isPrimitive ||
        isEnum ||
        isSealed ||
        equals(Boolean::class.java) ||
        equals(Byte::class.java) ||
        equals(Short::class.java) ||
        equals(Char::class.java) ||
        equals(Int::class.java) ||
        equals(Long::class.java) ||
        equals(Float::class.java) ||
        equals(Double::class.java) ||
        equals(String::class.java) ||
        Temporal::class.java.isAssignableFrom(this)