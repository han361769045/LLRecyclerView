# LLRecyclerView
##### Project website:[https://github.com/han361769045/LLRecyclerView](https://github.com/han361769045/LLRecyclerView)

### Description

LLRecyclerView is based on UltimateRecyclerView,and is a RecyclerView with pulling to refresh, loading more, animations ,header. remove those that do not often use.
You can use it ```just like RecyclerView```.
if you want to use more link UltimateRecyclerView website:[https://github.com/cymcsg/UltimateRecyclerView](https://github.com/cymcsg/UltimateRecyclerView)





# Dependency
```groove
 compile 'com.leo.lu:llrecyclerview:1.0.0'
```

# Usage
#### xml

```xml
   <com.leo.lu.llrecyclerview.LLRecyclerView
        android:id="@+id/ll_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:recyclerViewClipToPadding="true"
        app:recyclerViewEmptyView="@layout/empty_view"
        />
```
#### java code
```java
    LLRecyclerView llRecyclerView;
    public void onCreate(Bundle savedInstanceState){
    //your code
    llRecyclerView = findViewById(R.id.ll_recycler_view);
    llRecyclerView.setAdapter(myAdapter);
    enableLoadMore();
    refreshingMaterial();
  }
    
    //set loadmore
    public void enableLoadMore() {
        llRecyclerView.setOnLoadMoreListener(new LLRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                if (myAdapter.getItems().size() >= myAdapter.getTotal()) {
                    llRecyclerView.disableLoadmore();
                } else {
                    pageIndex++;
                    afterLoadMore();
                }
            }
        });
        llRecyclerView.reenableLoadmore();
    }


  /**
     * set Material refresh
     */
    public void refreshingMaterial() {
  
        llRecyclerView.setCustomSwipeToRefresh();
        materialHeader = new MaterialHeader(this);
        int[] colors = {Color.RED, Color.GRAY, Color.BLUE};

        materialHeader.setColorSchemeColors(colors);
        materialHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        materialHeader.setPadding(0, 15, 0, 10);
        materialHeader.setPtrFrameLayout(ultimateRecyclerView.mPtrFrameLayout);
        llRecyclerView.mPtrFrameLayout.removePtrUIHandler(storeHouseHeader);
        llRecyclerView.mPtrFrameLayout.autoRefresh(false);
        llRecyclerView.mPtrFrameLayout.setHeaderView(materialHeader);
        llRecyclerView.mPtrFrameLayout.addPtrUIHandler(materialHeader);
        llRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                isRefresh = true;
                pageIndex = 1;
                afterLoadMore();
            }
        });
    }

```


Thanks:
##### UltimateRecyclerView website:[https://github.com/cymcsg/UltimateRecyclerView](https://github.com/cymcsg/UltimateRecyclerView)

## License

``` 
Copyright 2016-present Leo_Lu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
