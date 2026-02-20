#!/bin/bash
egrep -o "href=\"/category/[/a-z_^\"]+" ../GrabFolder/Tubi/tubiHomePage.txt | sed 's/href=//g'
