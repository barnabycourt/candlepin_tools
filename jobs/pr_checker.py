#!/usr/bin/env python

import os

from bugzilla.rhbugzilla import RHBugzilla
from github3 import login

# Get Information from the environment
bugzilla_login = os.environ['bz.user']
bugzilla_pwd = os.environ['bz.password']
token = os.environ['github.token']
pr_id = os.environ['ghprbPullId']

BUGZILLA_URL = 'https://bugzilla.redhat.com'
BZ = RHBugzilla(url='%s/xmlrpc.cgi' % BUGZILLA_URL,
                user=bugzilla_login, password=bugzilla_pwd)

PR_TITLE_REGEX = '\A(\d+):'
BZ_GITHUB_TRACKER_REGEX = '\A(\w+)/(\w+)/pull/(\d+)\Z'
PR_EXTERNAL_TRACKER_TEMPLATE = '{owner}/{project}/pull/{pr_id}'


# get the pull request to validate
gh = login(token=token)
pr = gh.pull_request('candlepin', 'candlepin', pr_id)
if not pr:
    print "Unable to find the pull request"
    exit(1)

bz = BZ.getbug('1207721')

print bz
