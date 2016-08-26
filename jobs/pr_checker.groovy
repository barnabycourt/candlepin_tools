
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
        python {
            command('print "APPLES!"')
        }
    }
}