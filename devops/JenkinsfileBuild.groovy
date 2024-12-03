/* 
 * NOTE: This file is intended for use with the SDP Jenkins Templating Engine v2.0+
 * which supports fetching the job template and configuration from an SCM location.
 * 
 * Originally generated from: templates/devops/jte.JenkinsfileBuild.groovy.vm
 */

def gitId = 'github'
def gitRepo = 'https://github.com/csun-cpointe/test-483-final1.git'
def gitBranch = params.get("branch")
gitBranch = gitBranch ? gitBranch : "dev"

withGit url: gitRepo, cred: gitId, branch: gitBranch, {
    try {
        /* The config block uses the default maven-global-settings settings.xml configuration in the aiops-jenkins docker container */
        configFileProvider(
            [configFile(fileId: 'maven-global-settings', variable: 'MAVEN_GLOBAL_SETTINGS')]) {
            maven.run([profiles: ['ci']], ["clean", "deploy", "-U", "-Dhabushu.force.clean", "-s $MAVEN_GLOBAL_SETTINGS"])
        }
        slackSend color: "good",
                message: "test-483-final1 built successfully"
    } catch (err) {
        slackSend color: "danger",
                message: "test-483-final1 failed to build successfully"
        throw err
    }
}
