rootProject.name = "ecom"

include(":base")
project(":base").projectDir = File("../base")

include(":scheduler")
project(":scheduler").projectDir = File("../scheduler")

include(":jpa")
project(":jpa").projectDir = File("../jpa")

include(":auth")
project(":auth").projectDir = File("../auth")
