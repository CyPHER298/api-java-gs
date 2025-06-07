FROM ubuntu:latest
LABEL authors="henrique.souza"

ENTRYPOINT ["top", "-b"]