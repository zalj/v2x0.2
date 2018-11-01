package com.qm.v2x.util;


public class Geo {
	
	static double PI=3.14159265358979323846;
	
	static double hudu(double x) {
		double a0;
		a0=x*PI/180;
		return a0;
	}
	
	public static double[] convertToXY(double lon,double lat) {
		
		
		double B1, B2, L1, L2, S, A1, A2;
		double e2, W1, W2, sinu1, sinu2, cosu1, cosu2, L, a1, a2, b1, b2, g, g2, g0 = 0.0, r, p, q, sino, coso, o, sinA0, x, t1, t2, A, B, C, y;
		
		
		
		if((Math.abs(lon-125.4216320)<1e-6)&&(Math.abs(lat-43.7874910)<1e-6)) {
			double[] ans= {0.0,0.0};
			return ans;
		}
		
		 B1=hudu(43.7874910);
		 L1=hudu(125.4216320);
		 B2=hudu(lat);
		 L2=hudu(lon);
		
		/*白塞尔大地主题解算*/
		 e2=0.006693421622966;
		 W1=Math.sqrt(1-e2*Math.sin(B1)*Math.sin(B1));
		 W2=Math.sqrt(1-e2*Math.sin(B2)*Math.sin(B2));
		 sinu1=Math.sin(B1)*Math.sqrt(1-e2)/W1;
		 sinu2=Math.sin(B2)*Math.sqrt(1-e2)/W2;
		 cosu1=Math.cos(B1)/W1;
		 cosu2=Math.cos(B2)/W2;
		 L=L2-L1;
		 a1=sinu1*sinu2;
		 a2=cosu1*cosu2;
		 b1=cosu1*sinu2;
		 b2=sinu1*cosu2;
		/*逐次趋近法求解A1,A1是起点位置两点连线与N方向夹角*/
		 g=0;
		 r=L;
		
		while(true) {
			p=cosu2*Math.sin(r);
			q=b1-b2*Math.cos(r);
			A1=Math.atan(p/q);
			/*判断A1*/
			if(p>0 && q>0) {
				 A1=Math.abs(A1);
			}else if(p>0 && q<0) {
				A1=PI-Math.abs(A1);
			}else if(p<0 && q<0) {
				A1=PI+Math.abs(A1);
			}else {
				A1=2*PI-Math.abs(A1);
			}
			sino=p*Math.sin(A1)+q*Math.cos(A1);
			coso=a1+a2*Math.cos(r);
			o=Math.atan(sino/coso);
			/*判断o*/
			if(coso>0)
				o=Math.abs(o);
			else
				o=PI-Math.abs(o);
			sinA0=cosu1*Math.sin(A1);
			x=2*a1-(1-sinA0*sinA0)*coso;
			t1 = (33523299 - (28189 - 70 * (1 - sinA0*sinA0))*(1 - sinA0*sinA0))*1e-10;
			t2 = (28189 - 94 * (1 - sinA0*sinA0))*1e-10;
			g2 = (t1*o - t2*x*sino)*sinA0;
			if (Math.abs(g2 - g0) <= 1e-100)
				break;
			else
			{
				r = L + g2;
				g0 = g2;
			}
		}
		/*求解S*/
		A = 6356863.020 + (10708.949 - 13.474*(1 - sinA0*sinA0))*(1 - sinA0*sinA0);
		B = 10708.938 - 17.956*(1 - sinA0*sinA0);
		C = 4.487;
		y = ((1 - sinA0*sinA0)*(1 - sinA0*sinA0) - 2 * x*x)*coso;
		S = A*o + (B*x + C*y)*sino;
		/*求解A2*/
		A2 = Math.atan(cosu1*Math.sin(r) / (b1*Math.cos(r) - b2));
		/*判断A2*/
		if (p<0 && q<0)
			A2 = Math.abs(A2);
		else if (p<0 && q>0)
			A2 = PI - Math.abs(A2);
		else if (p>0 && q>0)
			A2 = PI + Math.abs(A2);
		else
			A2 = 2 * PI - Math.abs(A2);
		
		/*计算场景连线与场景坐标轴夹角以及对应坐标点*/

		
		
		double[] scenes = { S * Math.cos(0.5 * PI - A1),S * Math.sin(0.5 * PI - A1)};
		return scenes;
		
	}
}
