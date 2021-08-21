#!/usr/local/bin/python
# -*- coding: utf-8 -*-

import idna

before = 'ไทยร่วมใจ.com'
nomalized = idna.encode(before)
displayed = idna.decode(nomalized)


print(before)
print(nomalized.decode("utf-8"))
print(displayed)
