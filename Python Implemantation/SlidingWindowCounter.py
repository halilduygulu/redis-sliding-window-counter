#!/usr/bin/env python
# -*- coding: UTF-8 -*-
#
# @author: Uluc Furkan Vardar
# @updatedDate: 10.03.2020
# @version: 1.0.0

try:
	import redis
	from redis.client import Redis
	import time
	import random
except Exception as e:
	print ('Libs import error, ', str(e) )
	exit()

class SlidingWindowCounter():
	def __init__(self, host, port, db, windowInSecond):
		self.windowInSecond = windowInSecond
		self.host = host
		self.port = port
		self.db = db
		self.r = Redis( host = self.host, port=self.port, db=self.db) 

	def increment(self, key):
		currentMs = int(round(time.time() * 1000))		
		maxScoreMs = currentMs - self.windowInSecond*1000
		
		self.r.zremrangebyscore( key, 0, maxScoreMs )
		self.r.zadd( key, {currentMs : currentMs + random.random()} )
		self.r.expire( key, self.windowInSecond)

	def getCount(self, key):
		return self.r.zcard(key)


