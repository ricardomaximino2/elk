FROM docker.elastic.co/logstash/logstash:7.5.1@sha256:5bc89224f65459072931bc782943a931f13b92a1a060261741897e724996ac1a

ADD ./logstash/logstash.conf /usr/share/logstash/
ADD ./logs/elk.log /usr/share/logstash/