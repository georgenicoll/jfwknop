/* 
 * JFwknop is developed primarily by the people listed in the file 'AUTHORS'.
 * Copyright (C) 2016 JFwknop developers and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.cipherdyne.jfwknop;

import static com.cipherdyne.jfwknop.EnumFwknopRcType.*;
import com.cipherdyne.utils.InternationalizationHelper;

public enum EnumFwknopRcKey {

    ACCESS(PROTOCOL_PLUS_PORT, "i18n.spa.access"),
    ALLOW_IP(LOCAL_IP_ADDRESS, "i18n.spa.client.allowip", EnumFwknopdRcKey.SOURCE),
    ENCRYPTION_MODE(ENCRYPT_MODE, ""),
    DIGEST_TYPE(DIGEST_ALGORITHM, ""),

    USE_GPG(Y_N, "i18n.gpg.use"),
    USE_GPG_AGENT(Y_N, "i18n.gpg.use.agent"),
    GPG_SIGNING_PW(PASSPHRASE, "i18n.gpg.signing.password"),
    GPG_SIGNING_PW_BASE64(BASE64_PASSPHRASE, "i18n.gpg.signing.password.base64"),
    GPG_SIGNER(GPG_KEY_ID, "i18n.gpg.signer", EnumFwknopdRcKey.GPG_REMOTE_ID),
    GPG_RECIPIENT(GPG_KEY_ID, "i18n.gpg.recipient", EnumFwknopdRcKey.GPG_DECRYPT_ID),
    GPG_HOMEDIR(DIRECTORY_PATH, "i18n.gpg.homedir"),

    SPOOF_USER(STRING, "i18n.spa.spoof.user"),
    SPOOF_SOURCE_IP(IP_ADDRESS, "i18n.spa.spoof.source.ip"),
    RAND_PORT(Y_N, "i18n.spa.random.source.port"),
    KEY_FILE(FILE_PATH, ""),
    HTTP_USER_AGENT(STRING, ""),

    NAT_ACCESS(IP_PLUS_PORT, "i18n.nat.access.ip"),
    NAT_LOCAL(Y_N, "i18n.nat.local"),
    NAT_PORT(SINGLE_PORT, "i18n.nat.port"),
    NAT_RAND_PORT(Y_N, "i18n.nat.rand.port"),

    SPA_SERVER(IP_ADDRESS, "i18n.spa.server.ip"),
    SPA_SERVER_PORT(SINGLE_PORT, "i18n.spa.server.port"),
    SPA_SERVER_PROTO(PROTOCOL, "i18n.spa.client.proto"),
    KEY(PASSPHRASE, "i18n.rijndael.key", EnumFwknopdRcKey.KEY),
    KEY_BASE64(BASE64_PASSPHRASE, "i18n.rijndael.keybase64", EnumFwknopdRcKey.KEY_BASE64),

    USE_HMAC(Y_N, "i18n.hmac.use"),
    HMAC_KEY(PASSPHRASE, "i18n.hmac.key", EnumFwknopdRcKey.HMAC_KEY),
    HMAC_KEY_BASE64(BASE64_PASSPHRASE, "i18n.hmac.key.base64", EnumFwknopdRcKey.HMAC_KEY_BASE64),
    HMAC_DIGEST_TYPE(DIGEST_ALGORITHM, "i18n.hmac.digest.type", EnumFwknopdRcKey.HMAC_DIGEST_TYPE),

    SPA_SOURCE_PORT(SINGLE_PORT, "i18n.spa.client.sourceport"),
    FW_TIMEOUT(SECONDS, "i18n.misc.timeout"),
    RESOLVE_IP_HTTPS(Y_N, "i18n.resolve.ip.https"),
    RESOLVE_HTTP_ONLY(Y_N, "i18n.resolve.ip.http.only"),
    RESOLVE_URL(URL, "i18n.spa.client.resolveurl"),
    SERVER_RESOLVE_IPV4(Y_N, "i18n.spa.server.resolve.ipv4"),
    TIME_OFFSET(TIME, "i18n.misc.timeoffset");

    final private EnumFwknopRcType type;
    final private String i18Label;
    final private EnumFwknopdRcKey remoteKey;

    private EnumFwknopRcKey(EnumFwknopRcType type, String i18label) {
        this.type = type;
        this.i18Label = i18label;
        this.remoteKey = null;
    }

    private EnumFwknopRcKey(EnumFwknopRcType type, String i18label, EnumFwknopdRcKey remoteKey) {
        this.type = type;
        this.i18Label = i18label;
        this.remoteKey = remoteKey;
    }

    public EnumFwknopRcType getType() {
        return this.type;
    }

    public String getLabel() {
        return InternationalizationHelper.getMessage(this.i18Label);
    }

    public String getTooltip() {
        return InternationalizationHelper.getMessageOrNull(this.i18Label + ".tooltip");
    }

    public EnumFwknopdRcKey getRemoteKey() {
        return this.remoteKey;
    }
}
