#!/usr/bin/env python
# -*- coding: UTF-8 -*-
#
# @author: Uluc Furkan Vardar
# @updatedDate: 10.03.2020
# @version: 1.0.0

# --------------------------------------------------- TEST ---------------------------------------------------
import SlidingWindowCounter as swc
slidingWindowCounter = swc.SlidingWindowCounter( host = '.......' 	  ,
											 	 port = 6379      	  , 
											 	 db   = 10   	  	  ,
											 	 windowInSecond = 180  )

slidingWindowCounter.increment('uluc')
slidingWindowCounter.increment('uluc')
slidingWindowCounter.increment('uluc')
print ( slidingWindowCounter.getCount('uluc') )














