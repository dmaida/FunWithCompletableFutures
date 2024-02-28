# FunWithCompletableFutures
Fun with CompletableFutures!


### How to run

```shell
gradle run --args='--threads 1 --cpu --io'
```

### Usage

```shell
Usage: App [-chiV] [-p=<parallelism>] -t=<threads>
A simple application that runs CompletableFutures
  -c, --cpu                 Run CPU Bound Experiment
  -h, --help                Show this help message and exit.
  -i, --io                  Run IO Bound Experiment
  -p, --parallelism=<parallelism>
                            ForkJoinPool parallelism
  -t, --threads=<threads>   Number of CompletableFutures to create
  -V, --version             Print version information and exit.
```