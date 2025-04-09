/** Logical model for the Asteroid Rally game. */
public class AsteroidRallyModel {

	/**
	 * Asteroids.
	 */
	private Extent[] asteroids;

	/**
	 * Flags.
	 */
	private Flag[] flags;

	/**
	 * Player 1's ship.
	 */
	private Ship ship1;

	/**
	 * Player 2's ship.
	 */
	private Ship ship2;

	public AsteroidRallyModel() {
		// Create two ships, pointing north.
		ship1 = new Ship(0.25, 0.5, Math.PI / 2);
		ship2 = new Ship(0.75, 0.5, Math.PI / 2);
		// Create 10 asteroids that do not overlap.
		asteroids = new Extent[10];
		for (int i = 0; i < asteroids.length; i++) {
			do {
				asteroids[i] = new Extent(StdRandom.uniformDouble(), StdRandom.uniformDouble(), 0.05);
			} while (isConflictingAsteroidPosition(i));
		}
		// Create 5 flags that do not overlap.
		flags = new Flag[5];
		for (int i = 0; i < flags.length; i++) {
			do {
				flags[i] = new Flag(StdRandom.uniformDouble(), StdRandom.uniformDouble());
			} while (isConflictingFlagPosition(i));
		}
	}

	/**
	 * Causes both ships to drift and checks for flag hits.
	 */
	public void advance() {
		ship1.drift();
		ship2.drift();
		for (Flag f : flags) {
			if (f.getExtent().overlaps(ship1.getExtent())) {
				f.setHitByShip1();
			}
			if (f.getExtent().overlaps(ship2.getExtent())) {
				f.setHitByShip2();
			}
		}
	}

	/**
	 * Returns the asteroids.
	 */
	public Extent[] getAsteroids() {
		return asteroids;
	}

	/**
	 * Returns the flags.
	 */
	public Flag[] getFlags() {
		return flags;
	}

	/**
	 * Returns ship 1.
	 */
	public Ship getShip1() {
		return ship1;
	}

	/**
	 * Returns ship 2.
	 */
	public Ship getShip2() {
		return ship2;
	}

	/**
	 * Returns true if asteroid i overlaps with either ship or with any
	 * lower-indexed asteroid.
	 */
	public boolean isConflictingAsteroidPosition(int i) {
		if (asteroids[i].overlaps(ship1.getExtent())) {
			return true;
		}
		if (asteroids[i].overlaps(ship2.getExtent())) {
			return true;
		}
		for (int j = 0; j < i; j++) {
			if (asteroids[i].overlaps(asteroids[j])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if flag i overlaps with either ship, any asteroid, or any
	 * lower-indexed flag.
	 */
	public boolean isConflictingFlagPosition(int i) {
		if (ship1.getExtent().overlaps(flags[i].getExtent()) || ship2.getExtent().overlaps(flags[i].getExtent())) {
			return true;
		}

		for (Extent asteroid : asteroids) {
			if (asteroid.overlaps(flags[i].getExtent())) {
				return true;
			}
		}

		for (int j = 0; j < i; j++) {
			if (flags[j].getExtent().overlaps(flags[i].getExtent())) {
				return true;
			}
		}


		return false;
	}

	/**
	 * Returns 1 if player 1 has won, 2 if player 2 has won, or 0 if neither
	 * player has won. A player wins if they hit all flags first or if the other
	 * player hits a rock.
	 */
	public int winner() {
		Extent[] aster = this.asteroids;
		int cnt = aster.length;

		int s2;

		int p1Score = 0;


		for (s2 = 0; s2 < cnt; s2++) {
			Extent e = aster[s2];
			if (e.overlaps(this.ship2.getExtent()))
				return 1;


			if (e.overlaps(this.ship1.getExtent()))
				return 2;

		}

		s2 = 0;

		for (cnt = 0; cnt < this.flags.length; cnt++) {
			if (this.flags[cnt].hasBeenHitByShip1()) {
				p1Score++;
			}

			if (this.flags[cnt].hasBeenHitByShip2()) {
				s2++;
			}
		}

		if (p1Score >= 5)
			return 1;
		else if (s2 >= 5)
			return 2;
		else
			return 0;

	}
}
