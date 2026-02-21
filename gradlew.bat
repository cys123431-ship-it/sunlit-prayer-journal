@echo off
setlocal

where gradle >nul 2>nul
if %errorlevel%==0 (
  gradle %*
  exit /b %errorlevel%
)

echo gradle command not found. Install JDK + Gradle to run this project.
echo (GitHub Actions uses the standard Gradle environment.)
exit /b 1

