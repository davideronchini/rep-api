package com.rep.api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "30820942020100300D06092A864886F70D01010105000482092C308209280201000282020100C84E882ADC5F932939E08D0C46BEAE0018844A90D6048A2DFB2C95C5BD2329B2C84609F55EEA44FAF37B36C8C94B0A4ADC7C0236CFEA22993CE69ADF61EE5EBA414180AC2440EE81EC0C0B737FA31CF623E772E49CE05223BDF46414E85BF13E8218ACA24E53FD9A0468F8B346D2899F55734591E40DB68F5640CAC61C8C00A9991D8DCFAA238AC7E8195A591D10F0715D4A53107BB78ED695ACFDF749225DC27E08A592872C7B6040AA0564797FD3D73F345861FAB449A7F9BD82DB69FF699F3A26D384D21345F2FE7278FF8677E36D797D8B4D40D378A0D1D77B1E01E40355DD064FDA627CB5822E2CF1A6D27289944C0460C96982462E432DA9210AEE1BD004FA4AF1B01B215073275933FE38E5E3F3B044BD97EA760C58694DF833C1A190FFD0D094F89A7F4072DE30FE2B6A89B4E10D1FDFE915508A6610E479152C0C87F2E1D6F566046757989CA46B704244F7E860E5AAA65A5B1D7BBD405A7A05679D00F4E56EC2F7C60CFE30CE706F13E6A96BC16EF2CCF3DED22D140381FAB5E1A58CD53A54E39281674DAA2DDBF96FB33736B80805C9B2E6515140566A5EFF938E589EC827149E00C58A059A3856025B7D84F53B67818A48C92D206F236D518D8FC0EFD339A177F5C93D1A4E19ED784AE9C5D5B529EA161CB8E13917FEBD742E5B470857552BA5E8ADB9684A40619843B0C001E5568DE10DCBD41C79745FA7451902030100010282020008D5912DB0BFFA05D2B2BBB8764DF66F1F25B083359E8823473011754C90F13271CB9DA8A7D46B114002A7BE7B1AEC91338062F99CB9B867C1419B6ED835C83C41E0B17130D278A271711B8D2F172D7C669B7B3152D6403BD426CE9B84744FA36B34A037DD54037BE83A0D4E9CD99AC3BF4EA0F0C7AF2F7ACAB9FB5224540165CED0B89E82240F521F435A527D54C55753F466A2F40DE5B11848AE551A718CA8900B476AD066E4147142FE64B9736B8F534F369F05A204C5F18961422B93DDF4ED93E4F6CC96C32F98777482328C4AE87062B02D8AA5CE576B6C81412822060AA2AA4D59CB47B19AA9A3864F38FA0A657DBA7609540BB096DD941FE0E0479DA17EFD0BA5B37214AE4536B0A6F82207DCE4E8F5C5647C461752EEA5D1B0DDAD3384D212479F1CD77B0007DA24D6781BE8BA961BE13A2B9C029AC8FD240705F4A6B64C9ED885D74923F3A843C29D5A4F5BE4E2C13BE190DD025EA45496875C2A9C9228896999AAF35AC28E4A9DA262642BA619B899A31108489E1C58AA2D03246D3312A49DC38AF7AAFBFEECFEDAB9F14C0083059CE874012405502BD80D23249A6F08D6E9E96FC5C69FCEED215D47DEB7F0CB817418904502CE48D5C9E8FFF9B10038133C9E37EED1237EA02AB79138132CE56166C9E8BB41C9ED3061D8DEE73ECAA31D5206C7DF90033FC8228CC65E864A69F0478F1C7945D0D83F0576C14DFD0282010100E7FCA65BFA3A6A188D83605DEEF011F9E10E61E1EA294338C5958DB70CE2DDC1B997255893D159AA5F7860DDE589D758611050956DC682F2BAF247C22BC7CBC0429DD9058F5BDD42BC9AD92A4B7BA090A42779858999A38CD02A78598EA68B83DF7449AE6940E0C592800F4FCAFA547F9FAEF9FD3BB202B1FA876668D6380B4C5FC528BA40936F2C837A51C6D87C6AF1ADD687886389C596BFB08964B234EA4F85A8DC2FD9F67D4F6FEEDB3B95670ED959E1A4665ED08ED69F86822CBA2A3449677EB18686F8FE36162ADCE66E53BDF1A8B8495607574884177B7B22A1787C0B09E407D258028FDF9A168F56DB83BED3A03D8B0D2ACE8345E77DF7D6F492E4250282010100DD0A6646F3356D7DA774429D4AA1FE9A8944A12CEAEB2E3DE400B30CC8B8029C0EC0504F830AE2CE0728A7DCBABBDED0A0E3EEAE4D2607E374FB2D32A82D58E48967FD556EA6CC97167CC3912B8B1916D13471D2CE3ADA92C66BE2FE18E286DC044D57D44D00169224BFF532A29B51AAC40F8F852E6A1EC2A43AEDD75B7AAD27E5230CCAEDEB6D5EF6823AADAEE2778CCC32BEC09A1B4230D1AEDFDFFBBBB930367AE794DB83F11A83BB5D0E71C64E8E0DF135EC9F85EA1F410B1DFC9DA07E59A86A8B6945544073F87348C896A42870408D5A55CD9364BBC79FD7539C336CE718A3451D27E3F127359EAD4B80D8D58CE10A485644C58E48A76B6458B75D70E50282010100DD0C89BC14A7FA9660A97176B0094327B5C20D94C59A01BAA3D2C85146CF197CD189EB0506FCB192BD1BA83967D1BB47345DE65B2C59BE46E9C99884B550D09DE71C1F41C40345C6A48E6C2577FD59D6D903E0305517F7966278456B18992B5141E7BD999A233ECA23B9305DD8CC451DC29B690B5A0959B61B414F5CCFDA6131AA03E4B5EC784FB3BBA81F29F8F83CB302F0EAFE13D2A05AEF5698E441465889E3932E13CE7DA793BCD57D42BE2E90209D1B7B9A2BF85052C378D8AB23CCB93BC4891D4151750A4DA13BC077356348176FAC01FD1E4B96067F95A2943205EB345D7D51D2A517F3DD9D26A7F89C7C61A5A7075D6AF38209E5DF19D1C19A14299D028201007418E03B67C6AA299368770C92849F6B9FA992563B5527468E98E772518284AC5EFB17B38B8A8E31D484C01629663FD3A88DCF7171480660ABB2B9BC9FA4A1C9F779CD0FB03C9FBCC3ACE8333981A7BDDA480778C7A515E47D9F70EC3139D9C779E2FDBC9E6CEB6DFB2B6FA816CC298ADDCBA680372EB9D555D935CF25573BD7767C805D9DB5480ED6BEF3FF019A255CA86C06D0B6A0969579ACB61DB8E49292062B6BB33DD1013401F8C919AB1C9369FD5CB20C04859BC0AAB3221EEAD3BF496C31F9E362ECABA5BDDB1F616BEDF0E774A049D04357A55313B89AAA69A28112A8582916F53ABE10F012D5C690785341D97943213CA1DEE7BE37A3CF0D106119028201000A2A4332C0034B53A6517DA6AB56675663975612BD2877D8A07B3D6DFFE52399F1EBF0C85CEEC78EBFA357706EFB54BB6248FB403578B0B436753E1A5CA5B7DD49F7B9BC90C88643314B7350619148487F27AD70743CEF58122844D3FFE742CB5B96A3F78F1DD53FCC19B0D415A3C05B93C6A79BA794EC3886AD2BC8B49E268F8AE323F66940DC7C15DC0D98EA3E8656BF01F732AAEB79FE26BDEA4286666654B3C804DF2A8BFE3DF66791D2C78CE73AB024B0470F3D6794D9B81B8CC4AFBFDC6AE4FA0DC3FCB399DC929F02DE019ADC5851C19E98A7D09A095E5DB150B2139F295022EB9487F3ACC9410919A6E0B1BF28A8E091FE5CCE84F44F6D38135F9E61";
    private static final long JWT_EXPIRATION = 1000 * 60 * 30;
    private static final long REFRESH_EXPIRATION = 1000 * 3600 * 24 * 7;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, JWT_EXPIRATION);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, REFRESH_EXPIRATION);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}