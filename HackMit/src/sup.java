public boolean check(boolean[] on, int n, ArrayList<Clause> clauses) {
	for (Clause clause : clauses) {
		boolean ok = false;
		for (int i = 0; i < 3; i++) {
			if (on[clause.vars[i]] == clause.varNegs[i]) {
				ok = true;
			}
		}
		if (!ok) {
			return false;
		}
	}
	return true;
}

public boolean recur(boolean[] on, int n, ArrayList<Clause> clauses, int depth) {
	if (depth == n) {
		return check(on, n, clauses);
	}

	on[depth] = false;
	if (recur(on, n, clauses, depth + 1)) {
		return true;
	}

	on[depth] = true;
	if (recur(on, n, clauses, depth + 1)) {
		return true;
	}

	return false;
}

public boolean isGood(ArrayList<Clause> clauses) {
	int n = -1;
	for (Clause clause : clauses) {
		for (int i = 0; i < 3; i++) {
			n = Math.max(clause.vars[i], n);
		}
	}

	boolean on[] = new boolean[n];
	return recur(on, n, clauses, 0);
}

