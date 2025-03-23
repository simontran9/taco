# Contributing

## Development setup

The following developer dependencies assumes you are working (daily driving or remotely) on a Linux box.

**Dev dependencies**
- [ClangFormat](https://clang.llvm.org/docs/ClangFormat.html)
- [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
- [Valgrind](https://valgrind.org/)
- [perf](https://www.swift.org/documentation/server/guides/linux-perf.html)
- [hyperfine](https://github.com/sharkdp/hyperfine)

## Workflow

## Coding standards

Follow `.clang-format`. Some additional styling rules includes:
- `snake_case` for variables, and `PascalCase` for structures and enums names, `SCREAMING_SNAKE_CASE` for macros, constants, and enum values
- `+=` or `-=` over pre or post `++` or `--`
- only use `//`

## Testing

### Unit testing

### Fuzz testing with `libFuzzer`

## Debugging

## Benchmarking