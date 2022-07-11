package edu.bu.projectportal

data class Project(var id: Int, var title: String, var description: String, var author1: String, var author2: String, var author3: String, var link1: String, var link2: String, var link3: String, var key1: String, var key2: String, var key3: String, var favorite: Boolean){
    companion object {
//        var project = Project(0, "Weather Forecast", "Weather Forcast is an app ...")
//    }
        var curProjId:Int = 0
        var projects = mutableListOf(
            Project(0, "Weather Forecast", "Weather Forcast is an app ...", "Author1", "Author2", "Author3", "Link1", "Link2", "Link3", "Keyword1", "Keyword2", "Keyword3", false),
            Project(1, "Connect Me", "Connect Me is an app ... ", "Author1", "Author2", "Author3", "Link1", "Link2", "Link3", "Keyword1", "Keyword2", "Keyword3", false),
            Project(2, "What to Eat", "What to Eat is an app ...", "Author1", "Author2", "Author3", "Link1", "Link2", "Link3", "Keyword1", "Keyword2", "Keyword3", false),
            Project(3, "Project Portal", "Project Portal is an app ...", "Author1", "Author2", "Author3", "Link1", "Link2", "Link3", "Keyword1", "Keyword2", "Keyword3", false))
    }
}