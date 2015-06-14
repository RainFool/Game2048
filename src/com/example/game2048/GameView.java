package com.example.game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		initGameView();
	}

	private void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
		setOnTouchListener(new View.OnTouchListener() {

			// 起始点、终止点、x上偏移量、y上偏移量
			float startX;
			float startY;
			float offsetX;
			float offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				// 事件开始的时候存储游戏状态
//				save();
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						// 水平方向上
						if (offsetX < -5) {
							System.out.println("left");
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						// 垂直方向
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}

					break;

				}
				return true;
			}

		});
	}

	// 左右上下逻辑处理
	private void swipeLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				// 从当前位置向右遍历
				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cardsMap[x1][y].getNum() > 0) {
						// 如果当前值为空，后面有值
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							// 如果当前为空，后面有两个以上相同的值
							x--;

						}// 当前不为空，并两张卡片值相同,则合并
						else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
						}
						break;
					}
				}
			}

		}
		addRandomNum();
		addRandomNum();
	}

	private void swipeRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {

				// 从当前位置向右遍历
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardsMap[x1][y].getNum() > 0) {
						// 如果当前值为空，后面有值
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							// 如果当前为空，后面有两个以上相同的值
							x++;

						}// 当前不为空，并两张卡片值相同,则合并
						else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
						}
						break;
					}
				}
			}

		}
		addRandomNum();
		addRandomNum();
	}

	private void swipeUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {

				// 从当前位置向右遍历
				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum() > 0) {
						// 如果当前值为空，后面有值
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							// 如果当前为空，后面有两个以上相同的值
							y--;

						}// 当前不为空，并两张卡片值相同,则合并
						else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
						}
						break;
					}
				}
			}

		}
		addRandomNum();
		addRandomNum();
	}

	private void swipeDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y > 0; y--) {

				// 从当前位置向上遍历
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardsMap[x][y1].getNum() > 0) {
						// 如果当前值为空，后面有值
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							// 如果当前为空，后面有两个以上相同的值
							y++;

						}// 当前不为空，并两张卡片值相同,则合并
						else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
						}
						break;
					}
				}
			}

		}
		addRandomNum();
		addRandomNum();
	}

	private Card[][] cardsMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();// 空的点，记录可以产生随机数的地方

	private void addCards(int cardWidth, int cardHeight) {
		Card c;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				// 初始化全部卡片，并用数组记录
				c.setNum(0);
				addView(c, cardWidth, cardHeight);

				cardsMap[x][y] = c;
			}

		}
	}

	// 给没有数字的card上产生随机数并加入
	private void addRandomNum() {
		// 将可以产生随机数的区域用list存储
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		// 随机取出一个点
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	// 存储游戏当前状态
	private Card[][] save() {
		Card [][] tmp = new Card[cardsMap.length][]; 
		for (int i = 0; i < cardsMap.length; i++) {
			System.arraycopy(cardsMap[i], 0, tmp[i], 0, cardsMap.length);
		}
		System.out.println(Arrays.asList(cardsMap));
		System.out.println(Arrays.asList(tmp));
		return tmp;
	}

	@Override
	// 当屏幕分辨率发生改变的时候执行
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 初始化每张卡片的宽
		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCards(cardWidth, cardWidth);

		addRandomNum();
		addRandomNum();
	}
}
