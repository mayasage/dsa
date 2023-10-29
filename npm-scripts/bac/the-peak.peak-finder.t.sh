#! /usr/bin/sh

concurrently \
  "npm run the-peak:peak-finder:ct" \
  "npm run the-peak:peak-finder:lt"
