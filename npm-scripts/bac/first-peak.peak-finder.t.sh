#! /usr/bin/sh

concurrently \
  "npm run first-peak:peak-finder:ct" \
  "npm run first-peak:peak-finder:lt"
