package org.dreamwalker.newsreader.Interface;

import android.view.View;

/**
 *
 * 리사이클러 뷰에서 아이템을 클릭하기 위한 인터페이스를 추가한다
 * 왜냐하면 리스트 뷰의 경우 아이템 클릭 리스너가 존재하지만
 * 리사이클러 뷰의 경우 존재하지 않기 떄문에 만들어 줘야 한다.
 *
 * 메모리 효율성 면에서는 리사이클러뷰가 상당히 뛰어나기 때문에 리사이클러 뷰를 사용하자.
 *
 * Created by JAICHANGPARK on 10/10/17.
 */

public interface ItemClickListener {

    void onClick(View view, int posision, boolean isLongClick);
}
