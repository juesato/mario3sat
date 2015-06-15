f2 = open('compressed_3sat_map.json', 'w+')
buf = []
with open("simple_3sat_map.json") as f:
	# print "hello"
	for line in f.readlines():
		# print line
		clean = line.strip()
		buf.append(clean)

		if (clean[0:2] == '},'):
			# print "hello"
			for tmp in buf:
				f2.write(tmp)
			f2.write('\n')
			buf = []

for tmp in buf:
	f2.write(tmp)
