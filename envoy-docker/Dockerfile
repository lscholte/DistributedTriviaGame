FROM envoyproxy/envoy-dev:0c3702839c2f733654f1e74ec7708ffb67c6ec74
COPY envoy-custom.yaml /etc/envoy/envoy.yaml
RUN chmod go+r /etc/envoy/envoy.yaml
RUN ls /etc/envoy
CMD ["envoy" , "-p", "1000:1000", "-c", "/etc/envoy/envoy.yaml", "-l", "trace"]
