def gitId = 'github'
def gitRepo = 'https://github.com/csun-cpointe/test-483-final.git'
// Set branch in the Jenkins Job or set here if not variable
def gitBranch = 'refs/heads/${branch}'
def projectDirectory = 'test-483-final'
def jenkinsSteps
// Set URL of ArgoCD server
def argocdUrl = ''
def argocdAppName = 'test-483-final'
def argocdNamespace = 'argocd'
def argocdDestinationServer = 'https://kubernetes.default.svc'
def argocdBranch = '${branch}'
def argocdSyncLabel = 'argocd.argoproj.io/instance=test-483-final'

node('master') {
    dir(projectDirectory) {
        checkout([$class: 'GitSCM', branches: [[name: gitBranch]], userRemoteConfigs: [[credentialsId: gitId, url: gitRepo]]])
        jenkinsSteps = load 'devops/jenkinsPipelineSteps.groovy'
    }
}

stage("Authenticate") {
    node('master') {
        withCredentials([string(credentialsId: 'argocdPassword', variable: 'argocdPassword')]) {
            try {
                dir(projectDirectory) {
                    slackSend color: "warning",
                            message: "Verify ArgoCD local deploymebt authenticating"
                    jenkinsSteps.argocdAuthenticate(argocdUrl, '${argocdPassword}')
                }
            } catch (err) {
                slackSend color: "danger",
                        message: "Verify ArgoCD local deploymebt failed to authenticate properly"
                throw err
            }
        }
    }
}

stage("Deploy") {
    node('master') {
        try {
            dir(projectDirectory) {
                slackSend color: "warning",
                        message: "Verify ArgoCD local deploymebt deploying"
                jenkinsSteps.argocdDeploy(argocdAppName, argocdUrl, argocdDestinationServer, gitRepo, argocdBranch,
                        argocdNamespace, ['values.yaml'])
                slackSend color: "good",
                        message: "Verify ArgoCD local deploymebt deployed successfully"
            }
        } catch (err) {
            slackSend color: "danger",
                    message: "Verify ArgoCD local deploymebt failed to deploy properly"
            throw err
        }
    }
}

stage("Running") {
    node('master') {
        try {
            timeout(environmentUptime) {
                input message: 'Ready to kill the environment?', ok: 'Kill Test Server'
            }
        } catch (err) {}
    }
}

stage("Teardown") {
    node('master') {
        try {
            dir(projectDirectory) {
                slackSend color: "warning",
                        message: "Verify ArgoCD local deploymebt shutting down"
                jenkinsSteps.argocdTerminate(argocdAppName)
            }
        } catch (err) {
            slackSend color: "danger",
                    message: "Verify ArgoCD local deploymebt failed to shut down properly"
            throw err
        }
    }
}

