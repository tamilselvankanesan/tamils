package com.success.programs;

import java.util.ArrayList;
import java.util.List;

public class TrafficController {

	private List<Integer> snellSouth = new ArrayList<>();
	private List<Integer> snellNorth = new ArrayList<>();

	private List<Integer> weaverEast = new ArrayList<>();
	private List<Integer> weaverWest = new ArrayList<>();

	private void addCars() {
		System.out.println("add");
		new Thread(() -> {
			int loop = 0;
			while (loop < 10) {
				try {
					Thread.sleep(1000);
					snellNorth.add(1);
					snellSouth.add(1);

					weaverEast.add(1);
					weaverWest.add(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loop++;
			}

		}).start();

		new Thread(() -> {
			int loop = 0;
			while (loop < 10) {
				try {
					Thread.sleep(1000);
					System.out.println("loop " + (loop + 1) + " sn " + this.snellNorth.size() + " ss "
							+ this.snellSouth.size() + " we " + weaverEast.size() + " ww " + weaverWest.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loop++;
			}

		}).start();
	}

	private void snell() {

		new Thread(() -> {
			try {
				// Thread.sleep(1000);
				this.snellGreen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		;
	}

	private void weaver() {
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				this.weaverGreen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		;
	}

	private void snellGreen() throws InterruptedException {
		int itr = 0;
		while (true) {
			synchronized (this) {
				System.out.println("inside snell");
				if (itr == 2) {
					this.snellNorth.remove(0);
					this.snellSouth.remove(0);

					notify();

					Thread.sleep(4000);
					itr = 0;
				}

				if (itr == 1) {
					this.snellNorth.remove(0);
					this.snellSouth.remove(0);
				}
				Thread.sleep(1000);
				itr++;

			}
		}
	}

	private void weaverGreen() throws InterruptedException {
		int itr = 0;
		while (true) {
			synchronized (this) {
				System.out.println("inside weaver");
				if (itr == 2) {
					this.weaverEast.remove(0);
					this.weaverWest.remove(0);

					notify();

					Thread.sleep(4000);
					itr = 0;
				}

				if (itr == 1) {
					this.weaverEast.remove(0);
					this.weaverWest.remove(0);
				}
				Thread.sleep(1000);
				itr++;

			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TrafficController tc = new TrafficController();
		tc.addCars();
		Thread.sleep(1000);
		tc.snell();

		tc.weaver();

	}

}
