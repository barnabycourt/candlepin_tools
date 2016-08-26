
job('candlepin_pr_bugzilla_checker'){
    wrappers {
        // Enable access to the github & the bugzilla user
        credentialsBinding {
            string('github.token', 'github_api_token')
            usernamePassword('bz.user', 'bz.password', 'bugzilla_token')
        }
    }

    steps {
        shell('echo "first step"')
        shell ('python --version')
    }

    publishers {
        publishBuild {
            // only keep the last 10 builds for a maximum of 7 days
            discardOldBuilds(7, 10)
        }
    }
}