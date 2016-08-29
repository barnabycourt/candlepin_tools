#!/usr/bin/env python

import os

from bugzilla.rhbugzilla import RHBugzilla

print 'Trying Something New'

bugzilla_login = os.environ['bz.user']
bugzilla_pwd = os.environ['bz.password']

BUGZILLA_URL = 'https://bugzilla.redhat.com'
BZ = RHBugzilla(url='%s/xmlrpc.cgi' % BUGZILLA_URL,
                user=bugzilla_login, password=bugzilla_pwd)
bz = BZ.getbug('1207721')

print bz
