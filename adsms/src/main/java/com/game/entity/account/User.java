package com.game.entity.account;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.game.entity.AuditableEntity;
import com.game.entity.LabelValue;
import com.game.modules.utils.ReflectionUtils;

@XmlRootElement(name = "user")
// 指定子节点的顺序
@XmlType()
@Entity
@Table(name = "ACC_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AuditableEntity implements UserDetails {

    private static final long serialVersionUID = 3275659017848936331L;

    private String username; // required 登录名
    private String email; // required; unique
    private String password;
    private String realName;
    private Integer version;
    private List<Role> useRoles = new LinkedList<Role>();
    private List<Role> grantRoles = new LinkedList<Role>();
    private Boolean enabled = true;// 账户可用
    private Boolean accountExpired = false;// 帐号过期
    private Boolean accountLocked = false;// 帐号锁定
    private Boolean credentialsExpired = false;// 证书过期

    private String phone;
    private Domain domain;// 用户所属域
    private String description;

    private String customId; // 客户id
    private String userGroup; // 用户组： 1、商务；2、客户；3、其他

    @XmlAttribute
    @Override
    @Column(length = 150)
    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(Long id, String username, String email, String password, String realName, Integer version, Boolean enabled, Boolean accountExpired, Boolean accountLocked,
            Boolean credentialsExpired, String phone, String description) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.realName = realName;
        this.version = version;
        this.enabled = enabled;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.phone = phone;
        this.description = description;
    }

    @XmlAttribute
    @Override
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    @XmlAttribute
    @Column(name = "real_name", nullable = false, length = 150)
    public String getRealName() {
        return realName;
    }

    @XmlAttribute
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    @XmlAttribute
    @Column
    public String getPhone() {
        return phone;
    }

    @XmlTransient
    @Version
    // @Transient
    public Integer getVersion() {
        return version;
    }

    @XmlAttribute
    @Override
    @Column(name = "account_enabled")
    public boolean isEnabled() {
        return enabled;
    }

    @XmlAttribute
    @Column(name = "account_expired", nullable = false)
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @XmlAttribute
    @Column(name = "account_locked", nullable = false)
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @XmlAttribute
    @Column(name = "credentials_expired", nullable = false)
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    // (fetch = FetchType.EAGER)(cascade = { CascadeType.ALL })
    @ManyToMany
    @JoinTable(name = "ACC_USER_ROLE_USE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    // @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "hbCache")
    public List<Role> getUseRoles() {
        return useRoles;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    @XmlTransient
    @ManyToMany
    @JoinTable(name = "ACC_USER_ROLE_GRANT", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    // @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "hbCache")
    public List<Role> getGrantRoles() {
        return grantRoles;
    }

    @XmlAttribute
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "create_time")
    // public Date getCreateTime() {
    // return createTime;
    // }

    // @Column(name = "create_user")
    // public String getCreateUser() {
    // return createUser;
    // }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "domain_id")
    public Domain getDomain() {
        return domain;
    }

    // ----------------------- @Transient
    /**
     * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
     */
    @Transient
    @XmlTransient
    public String getUseRoleNames() {
        return ReflectionUtils.convertElementPropertyToString(useRoles, "name", ", ");
    }

    /**
     * 用户可支配的角色名称字符串, 多个角色名称用','分隔.
     */
    @Transient
    @XmlTransient
    public String getGrantRoleNames() {
        return ReflectionUtils.convertElementPropertyToString(grantRoles, "name", ", ");
    }

    /**
     * 用户拥有的角色id字符串, 多个角色id用','分隔.
     */
    @Transient
    @XmlTransient
    @SuppressWarnings("unchecked")
    public List<Long> getUseRoleIds() {
        return ReflectionUtils.convertElementPropertyToList(useRoles, "id");
    }

    /**
     * 可授权角色
     */
    @Transient
    @XmlTransient
    @SuppressWarnings("unchecked")
    public List<Long> getGrantRoleIds() {
        return ReflectionUtils.convertElementPropertyToList(grantRoles, "id");
    }

    /**
     * Convert user useRoles to LabelValue objects for convenience.
     * 
     * @return a list of LabelValue objects with role information
     */
    @XmlTransient
    @Transient
    public List<LabelValue> getRoleList() {
        List<LabelValue> userRoles = new ArrayList<LabelValue>();

        if (this.useRoles != null) {
            for (Role role : useRoles) {
                // convert the user's useRoles to LabelValue Objects
                userRoles.add(new LabelValue(role.getName(), role.getCode()));
            }
        }
        return userRoles;
    }

    /**
     * Adds a role for the user
     * 
     * @param role
     *            the fully instantiated role
     */
    public void addRole(Role role) {
        getUseRoles().add(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        // if (this == o) {
        // return true;
        // }
        // if (!(o instanceof User)) {
        // return false;
        // }
        // final User user = (User) o;
        // return !(username != null ? !username.equals(user.getUsername()) :
        // user.getUsername() != null);

        boolean res = false;
        if (o != null && User.class.isAssignableFrom(o.getClass())) {
            User s = (User) o;
            res = new EqualsBuilder().append(username, s.getUsername()).isEquals();
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // return (username != null ? username.hashCode() : 0);
        return new HashCodeBuilder(11, 23).append(username).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", this.username).append("realName", this.realName).append("email", this.email)
                .append("enabled", this.enabled).append("domain", getDomain().getLabel()).append("authority[size]", getAuthorities().size())
                .append("domain.children.size", getDomain().getChildrenNum());
        return sb.toString();
    }

    // ---------- springSecurity
    private Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 仅包含裸权限信息
     * 
     * @return GrantedAuthority[] an array of useRoles.
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    @Transient
    @XmlTransient
    public Set<GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(authorities) && CollectionUtils.isNotEmpty(getUseRoles())) {
            for (Role role : getUseRoles()) {
                for (Authority a : role.getAuthorities()) {
                    authorities.add(a.getGrantedAuthorityCore());
                }
            }
        }
        return authorities;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    @Transient
    @XmlTransient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    @Transient
    @XmlTransient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    @Transient
    @XmlTransient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    // ---------------------- SET

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setUseRoles(List<Role> useRoles) {
        this.useRoles = useRoles;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGrantRoles(List<Role> grantRoles) {
        this.grantRoles = grantRoles;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

}
