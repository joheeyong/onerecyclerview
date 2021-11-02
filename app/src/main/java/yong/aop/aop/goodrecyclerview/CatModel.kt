package yong.aop.aop.goodrecyclerview

class CatModel {

    companion object {
        const val Maincat = 1
        const val Mainsub = 2
    }

    var type: Int
    lateinit var catbig : Catbig
    lateinit var catsmall : Catsmall
    var isExpanded : Boolean

    constructor(type : Int, catbig : Catbig, isExpanded : Boolean = false){
        this.type = type
        this.catbig = catbig
        this.isExpanded = isExpanded
    }

    constructor(type : Int, catsmall : Catsmall, isExpanded : Boolean = false){
        this.type = type
        this.catsmall = catsmall
        this.isExpanded = isExpanded
    }


}