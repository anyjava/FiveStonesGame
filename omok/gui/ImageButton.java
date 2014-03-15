package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * @author Mr.son
 * @version 1.0
 * @since 2005-10-30
 * 
 * 이 클래스는 이미지로 된 버튼을 사용하기 위한 컴포넌트이다.
 */
public class ImageButton extends JButton {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private Image image;
	private Image currentImage;
	private Image rolloverImage;
	private Image hitImage;
	
	/**
	 * 단순하게 그림파일 하나로만 된 버튼 생성.
	 * @param imageUrl 스트링으로 된 그림파일 주소
	 */
	public ImageButton(String imageUrl, String text) {
		super();
		currentImage = new ImageIcon(
				URLGetter.getResource(imageUrl)
				).getImage();
		
		image = currentImage;
		addEvent();
		
		this.setText(text);

		width = image.getWidth(null);
		height = image.getHeight(null);
		this.setSize(width, height);
	}
	
	/**
	 * 현재 이미지와 Roll Over되었을 때의 이미지 두개를 가지고 생성.
	 * @param imageUrl 기본 이미지 파일주소
	 * @param rolloverImageUrl 롤 오버 되었을 때 표시되는 이미지 주소
	 */
	public ImageButton(String imageUrl, String text, String rolloverImageUrl) {
		this(imageUrl, text);
		rolloverImage = new ImageIcon(
				URLGetter.getResource(rolloverImageUrl)
				).getImage();
	}
	
	/**
	 * 현재이미지, Roll Over, Hit 상태의 세가지 이미지로 버튼 생성
	 * @param imageUrl 기본 이미지 파일주소
	 * @param rolloverImageUrl 롤 오버 되었을 때 표시되는 이미지 주소
	 * @param hitImageUrl 버튼을 클릭했을때 표시되는 이미지 주소
	 */
	public ImageButton(String imageUrl, String text, 
			String rolloverImageUrl, String hitImageUrl) {
		
		this(imageUrl, text, rolloverImageUrl);
		hitImage = new ImageIcon(
				URLGetter.getResource(hitImageUrl)
				).getImage();
	}
	
	/**
	 * 메소드 오버라이딩. 이미지를 그려주는 메소드.
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
//		super.paint(g);   이 메소드를 실행 시키면 원래 버튼 모양이 나온다.
//		System.out.println("BUTTON");
		g.drawImage(image, 0,0, width, height, null);
	}
	
	/**
	 * 버튼의 이미지를 바꾸는 메소드
	 * @param imgUrl 바꿀이미지의 경로
	 */
	public void setImage(String imgUrl) {
		currentImage = new ImageIcon(
				URLGetter.getResource(imgUrl)).getImage();
	}
	
	/*
	 * 이벤트를 등록하는 메소드이다.
	 * 각 상태별로 Image가 null상태일때는 paint()를 호출하지 않는다.
	 */
	private void addEvent() {
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				if (rolloverImage != null) {
					image = rolloverImage;
					paint(getGraphics());
				}
			}
			public void mouseExited(MouseEvent arg0) {
				if (currentImage != null) {
					image = currentImage;
					paint(getGraphics());
				}
			}
			public void mouseClicked(MouseEvent arg0) {
				if (hitImage != null) {
					image = hitImage;
					paint(getGraphics());
				}
			}
		});
	}
}
