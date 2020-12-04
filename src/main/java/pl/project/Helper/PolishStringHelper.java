package pl.project.Helper;

public class PolishStringHelper {
    public static String replacePolishCharacters(String text){
        StringBuilder sb = new StringBuilder(text);
        for (int I = 0; I < text.length(); I++)
        {
            switch (sb.charAt(I))
            {
                case 'ą': sb.setCharAt(I,'a'); break;
                case 'ć': sb.setCharAt(I,'c'); break;
                case 'ę': sb.setCharAt(I,'e'); break;
                case 'ł': sb.setCharAt(I,'l'); break;
                case 'ń': sb.setCharAt(I,'n'); break;
                case 'ó': sb.setCharAt(I,'o'); break;
                case 'ś': sb.setCharAt(I,'s'); break;
                case 'ź': sb.setCharAt(I,'z'); break;
                case 'ż': sb.setCharAt(I,'z'); break;
                case 'Ą': sb.setCharAt(I,'A'); break;
                case 'Ć': sb.setCharAt(I,'C'); break;
                case 'Ę': sb.setCharAt(I,'E'); break;
                case 'Ł': sb.setCharAt(I,'L'); break;
                case 'Ń': sb.setCharAt(I,'N'); break;
                case 'Ó': sb.setCharAt(I,'O'); break;
                case 'Ś': sb.setCharAt(I,'S'); break;
                case 'Ź': sb.setCharAt(I,'Z'); break;
                case 'Ż': sb.setCharAt(I,'Z'); break;
            }
        }
        return sb.toString();
    }
}
