# redis-sliding-window-counter
Sliding window counter implementation based on Redis sorted sets 

It uses ZSET data type of Redis to count for every key. Multiple increments in a millisecond is supported.

There are 3 implementations for [Node.js](https://github.com/halilduygulu/redis-sliding-window-counter-nodejs) [Java](https://github.com/halilduygulu/redis-sliding-window-counter) and [Python](https://github.com/UlucFVardar/redis-sliding-window-counter).
