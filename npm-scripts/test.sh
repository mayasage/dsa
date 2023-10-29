#! /usr/bin/sh

exec_coverage_test() {
  jest --coverage "$1"
}

exec_load_test() {
  ts-node "$1"
}

# Print Pretty
bold=$(tput bold)
normal=$(tput sgr0)

# Error Check
if [ $# -eq 0 ]; then
  echo "No argument received 🌞"
  echo "${bold}Usage: ${normal}npm run test -- peak-finder:first-peak:lt"
  exit 1
fi

# Build Base Path
base_path=$(pwd)/"$1"
base_path=$(echo "$base_path" | sed -e "s/:/\//g") # replace colon with /

# Conditionally Execute
last_three=$(echo "${base_path}" | tail -c 3)

if [ "$last_three" = "/t" ]; then
  # Execute both tests
  ct_path=$(echo "$base_path" | sed -e "s/t$/index.test.ts/")
  lt_path=$(echo "$base_path" | sed -e "s/t$/load-test.ts/")
  exec_coverage_test "$ct_path"
  exec_load_test "$lt_path"
else
  # Execute only 1 test
  last_four=$(echo "${base_path}" | tail -c 4)
  if [ "$last_four" = "/ct" ]; then
    path=$(echo "$base_path" | sed -e "s/ct$/index.test.ts"/)
    exec_coverage_test "$path"
  elif [ "$last_four" = "/lt" ]; then
    path=$(echo "$base_path" | sed -e "s/lt$/load-test.ts/")
    exec_load_test "$path"
  else
    echo "Invalid Path: ${base_path}"
    exit 1
  fi
fi
