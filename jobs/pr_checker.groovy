//def toolsGitUrl = 'git@github.com:barnabycourt/candlepin_tools.git'
//def candlepinGitUrl = 'git@github.com:barnabycourt/candlepin.git'

//def String pythonScript = 'print \'pear\''

job('candlepin_pr_bugzilla_checker') {

    // Keep the last 10 builds
    logRotator(-1, 10)
//    scm {
//        git(toolsGitUrl)
//    }
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
        python {
            command(readFileFromWorkspace('jobs/pr_checker.py'))
//            command(pythonScript)
            nature('python')
        }
    }
}