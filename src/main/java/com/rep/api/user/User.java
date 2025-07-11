package com.rep.api.user;

import com.rep.api.achievement.Achievement;
import com.rep.api.customization.Customization;
import com.rep.api.emoji.Emoji;
import com.rep.api.event.Event;
import com.rep.api.friendship.Friendship;
import com.rep.api.medal.Medal;
import com.rep.api.mission.Mission;
import com.rep.api.post.reaction.PostReaction;
import com.rep.api.post.tag.PostTag;
import com.rep.api.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(unique = true)
    private String tag;

    @OneToMany(mappedBy = "user")
    private Set<PostTag> tags;

    @OneToMany(mappedBy = "user")
    private Set<PostReaction> reactions;

    @OneToMany(mappedBy = "creator")
    private Set<Friendship> outgoingFriendships;

    @OneToMany(mappedBy = "receiver")
    private Set<Friendship> incomingFriendships;

    @ManyToMany(mappedBy = "users")
    private Set<Emoji> emojis;

    @OneToMany(mappedBy = "user")
    private Set<Medal> medals;

    @ManyToMany(mappedBy = "users")
    private Set<Achievement> achievements;

    @ManyToMany(mappedBy = "users")
    private Set<Mission> missions;

    @ManyToMany(mappedBy = "users")
    private Set<Event> events;

    @OneToOne(mappedBy = "user")
    private Customization customization;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
