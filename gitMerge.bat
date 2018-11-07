set /p branch="Enter branch to merge to master: "
git checkout master
git pull
git checkout %branch%
git merge master
git status
set /p wait="exit terminal if not ok..."
git checkout master
git merge %branch%
git push origin master
cmd /k git status
