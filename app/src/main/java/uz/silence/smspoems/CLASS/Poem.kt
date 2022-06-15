package uz.silence.smspoems.CLASS

class Poem {

    var id:Int? = null
    var largeTitle:String? = null
    var littleTitle:String? = null
    var textPoem:String? = null
    var isHave:Boolean? = null


    constructor(
        id: Int?,
        largeTitle: String?,
        littleTitle: String?,
        textPoem: String?,
        isHave: Boolean?
    ) {
        this.id = id
        this.largeTitle = largeTitle
        this.littleTitle = littleTitle
        this.textPoem = textPoem
        this.isHave = isHave
    }

    constructor()


}