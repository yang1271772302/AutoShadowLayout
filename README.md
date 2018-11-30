AutoShadowLayout

A layout,can auto or set yourself to use shadow

这是一个自动加阴影（蒙层）的控件，里面可以任意子布局和控件

使用方法：

1：引入

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.yang1271772302:AutoShadowLayout:V1.0'
	}


2：使用

2.1）
XML：


            <com.bobby.autolayout.AutoShadowLayout
                android:id="@+id/auto1"
                android:layout_width="match_parent"
                android:layout_height="60dp"
                bobby:shadow_press="true">

                <TextView
                    android:id="@+id/button1"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:text="手动档默认蒙层"
                    android:gravity="center"
                    android:background="#0fff"/>

            </com.bobby.autolayout.AutoShadowLayout>


2.2）
代码：

 shadow4 = (AutoShadowLayout) findViewById(R.id.auto4);

        shadow2.setPress(sha2);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sha1 = !sha1;
                shadow1.setPress(sha1);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sha2 = !sha2;
                shadow2.setPress(sha2);
            }
        });

        shadow4.setOnClickListener(new AutoShadowLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });


注意事项：

该控件解决了跟ScrollView的滑动冲突，因此使用的时候请使用该控件自已定义的onClickListener


关键方法和说明：

shadow_press_color：设置阴影蒙层的颜色，这个是覆盖颜色，使用argb的颜色效果会更好

shadow_press：设置阴影蒙层是否显示，true显示，false不显示，默认为false

shadow_auto：设置阴影蒙层是否为自动。为了增强性能增加此开关设置，默认为false，使用根据情况来开启

shadow_can_click：设置阴影蒙层是否可带点击。true表示接通了onClick的回调，false表示进制点击，默认为false

最后附上使用效果GIF：

![gif](https://github.com/yang1271772302/AutoShadowLayout/raw/master/testgif.gif)

看不了的小伙伴在项目里点开GIF即可查看
