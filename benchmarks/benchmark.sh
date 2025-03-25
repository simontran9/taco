#!/usr/bin/env bash

hyperfine_installed () {
    if ! command hyperfine --version 2>&1>/dev/null; then
        echo "Error: hyperfine is not installed."
        exit 1
    fi
}

benchmark () {
    #IMPL1="./your_program --option1"
    #IMPL2="./your_program --option2"
    #IMPL3="./your_program --optimized"
    #
    #hyperfine --warmup 3 --min-runs 10 \
    #    --export-json results.json \
    #    --export-markdown results.md \
    #    "$IMPL1" "$IMPL2" "$IMPL3"
    echo "Hello"
}

main() {
    hyperfine_installed
    benchmark
}

main