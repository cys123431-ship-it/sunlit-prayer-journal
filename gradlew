#!/usr/bin/env sh
if command -v gradle >/dev/null 2>&1; then
  gradle "$@"
  exit $?
fi

echo "gradle command not found. Install JDK + Gradle to run this project."
exit 1

