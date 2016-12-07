# ~/bin/forge-distribution-3.3.3.Final/bin/forge -e "run create-portal-with-forge.fsh"
project-new --named rhmf-admin --top-level-package com.redhat.demos.rhmfk --stack JAVA_EE_7
jpa-new-entity --named Device
jpa-new-field --named deviceId 
jpa-new-field --named voterName --length 50
cd ..
java-new-enum --named VoteSelection --target-package com.redhat.demos.rhmfk
java-add-enum-const --named YELLOW RED GREEN
cd ..
jpa-new-entity --named Vote --target-package com.redhat.demos.rhmfk
jpa-new-field --named deviceId
jpa-new-field --named pollName
jpa-new-field --named voteSelection --type com.redhat.demos.rhmfk.VoteSelection
cd ..
jpa-new-entity --named Poll --target-package com.redhat.demos.rhmfk
jpa-new-field --named name
jpa-new-field --named isOpen
jpa-new-field --named isDefault
jpa-new-field --named createTime --type java.util.Date --temporalType DATE
jpa-new-field --named startTime --type java.util.Date --temporalType DATE
jpa-new-field --named isArchived
jpa-new-field --named vote --type com.redhat.demos.rhmfk.Vote --relationship-type One-to-Many
cd ..
scaffold-generate --provider AngularJS --targets com.redhat.demos.rhmfk.*
rest-generate-endpoints-from-entities --targets com.redhat.demos.rhmfk.*

# make sure you have installed java sdk and install Wildfly:
# addon-install-from-git --url https://github.com/forge/as-addon  --coordinate org.jboss.forge.addon:as
# addon-install-from-git --url https://github.com/forge/jboss-as-addon  --coordinate org.jboss.forge.addon:jboss-as-wf
# as-setup --server wildfly  --version 10.1.0.Final
# build
# as-start
# as-deploy
