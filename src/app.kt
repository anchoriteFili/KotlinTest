import javafx.beans.property.ReadOnlyProperty
import jdk.management.resource.ResourceId
import kotlin.reflect.KProperty

class ResourceID() {
    val image_id: String = "101"
    val text_id: String = "102"
}

class ResourceLoader(id: ResourceID) {
    val d: ResourceID = id

    operator fun provideDelegate(thisRef: MyUI, prop: KProperty): ReadOnlyProperty {

        if (checkProperty(thisRef, prop.name)) {
            return DellImpl(d)
        } else {
            throw Exception("Error ${prop.name}")
        }
    }

    private fun checkProperty(thisRef: MyUI, name: String):Boolean {

        if (name.equals("image") || name.equals("text")) {
            return true
        } else {
            return false
        }
    }
}

class DellImpl(d:ResourceID) : ReadOnlyProperty {
    val id: ResourceID = d

    override fun getValue(thisRef: MyUI, property: KProperty): String {

        if (property.name.equals("image"))
            return property.name+" "+id.image_id
        else
            return property.name+" "+id.text_id
    }
}

fun bindResource(id: ResourceID): ResourceLoader {
    var res = ResourceLoader(id)
    return res
}

class MyUI {
    val image by bindResource(ResourceID())
    val text by bindResource(ResourceID())
}


fun main(args: Array<String>) {

    try {
        var ui = MyUI()
        println(ui.image)
        println(ui.text)
    } catch (e: Exception) {
        println(e.message)
    }
}









