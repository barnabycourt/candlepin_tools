//def toolsGitUrl = 'git@github.com:barnabycourt/candlepin_tools.git'
def String candlepinGitUrl = 'git@github.com:barnabycourt/candlepin.git'
//def String[] github_admin_whitelist = ['barnabycourt']

job('candlepin_pr_bugzilla_checker') {

    // Keep the last 10 builds
    logRotator(-1, 10)
//    scm {
//        git(toolsGitUrl)
//    }
    scm {
        git {
            remote {
                github(candlepinGitUrl)
                refspec('+refs/pull/*:refs/remotes/origin/pr/*')
            }
            branch('${sha1}')
        }
    }
    triggers {
        githubPullRequest {
            admins(['barnabycourt'])
//            userWhitelist('you@you.com')
//            userWhitelist(user_whitelist)
//            orgWhitelist('my_github_org')
//            orgWhitelist(['your_github_org', 'another_org'])
//            cron('H/5 * * * *')
//            triggerPhrase('OK to test')
//            onlyTriggerPhrase()
//            useGitHubHooks()
//            permitAll()
//            autoCloseFailedPullRequests()
//            allowMembersOfWhitelistedOrgsAsAdmin()
//            extensions {
//                commitStatus {
//                    context('Checking the GitHub & Bugzilla bookkeeping')
//                    triggeredStatus('starting deployment to staging site...')
//                    startedStatus('deploying to staging site...')
////                    statusUrl('http://mystatussite.com/prs')
//                    completedStatus('SUCCESS', 'All is well')
//                    completedStatus('FAILURE', 'Something went wrong. Investigate!')
//                    completedStatus('PENDING', 'still in progress...')
//                    completedStatus('ERROR', 'Something went really wrong. Investigate!')
//                }
//            }
        }
    }
    wrappers {
        // Enable access to the github & the bugzilla user
        credentialsBinding {
            string('github.token', 'github_api_token')
            usernamePassword('bz.user', 'bz.password', 'bugzilla_token')
        }
    }

    steps {
        python {
            command(readFileFromWorkspace('jobs/pr_checker.py'))
            nature('python')
        }
    }
}