def deployToArtifactory() {
    def artifactoryUrl = 'http://<EC2IP>:8082/artifactory/example-repo-local/'
    def jarFileName = 'kubernetes-configmap-reload-0.0.1-SNAPSHOT.jar'
    def username = 'admin'
    def password = 'your-artifactory-password' // Replace with your Artifactory password

    def curlCommand = """
        curl -X PUT -u $username:$password -T $jarFileName $artifactoryUrl
    """

    def process = curlCommand.execute()
    process.waitFor()

    if (process.exitValue() == 0) {
        println "Artifactory deployment successful!"
    } else {
        println "Artifactory deployment failed. Exit code: ${process.exitValue()}"
        println "Error Output: ${process.err.text}"
    }
}

deployToArtifactory()
