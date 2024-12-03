/* 
 * NOTE: This file is intended for use with the SDP Jenkins Templating Engine v2.0+
 * which supports fetching the job template and configuration from an SCM location.
 * 
 * Originally generated from: templates/devops/jte.JenkinsfileRelease.groovy.vm
 */

def gitId = 'github'
def gitRepo = 'https://github.com/csun-cpointe/test-483-final1.git'
def gitBranch = params.get("releaseBranch")
gitBranch = gitBranch ? gitBranch : "dev"

withGit url: gitRepo, cred: gitId, branch: gitBranch, {
    try {
        /* The config block uses the default maven-global-settings settings.xml configuration in the aiops-jenkins docker container */
        configFileProvider(
            [configFile(fileId: 'maven-global-settings', variable: 'MAVEN_GLOBAL_SETTINGS')]) {
                maven.run([profiles: ['ci']], ["release:clean","release:prepare", "release:perform", "-U", "-B",
                    "-Dhabushu.force.clean", "-DreleaseVersion=${releaseVersion}", "-DdevelopmentVersion=${developmentVersion}",
                    "\"-DscmCommentPrefix=${scmCommentPrefix}\"", "-s $MAVEN_GLOBAL_SETTINGS"])
        }

        slackSend color: "good",
                message: "test-483-final1 released successfully"

    } catch (err) {
        slackSend color: "danger",
                message: "test-483-final1 failed to release successfully"
        throw err
    }
}
