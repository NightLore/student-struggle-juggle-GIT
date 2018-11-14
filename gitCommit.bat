git add -A
set /p message="Enter commit message: "
git commit -m "%message%"
cmd /k git status
