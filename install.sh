#!/usr/bin/env sh

set -e

VERSION="v0.1.0"
GITHUB_USER="ktorite"
REPO="ktorite-cli"
INSTALL_DIR="$HOME/.ktorite"
JAR_URL="https://github.com/$GITHUB_USER/$REPO/releases/download/$VERSION/ktorite-all.jar"

echo "Downloading ktorite v$VERSION"

mkdir -p "$INSTALL_DIR"
curl -L -o "$INSTALL_DIR/ktorite.jar" "$JAR_URL"

cat >"$INSTALL_DIR/ktorite" <<EOF
#!/usr/bin/env sh
exec java -jar "$INSTALL_DIR/ktorite.jar" "\$@"
EOF

chmod +x "$INSTALL_DIR/ktorite"

if [ -n "$ZSH_VERSION" ]; then
  PROFILE="$HOME/.zshrc"
elif [ -n "$BASH_VERSION" ]; then
  PROFILE="$HOME/.bashrc"
elif [ -n "$FISH_VERSION" ]; then
  PROFILE="$HOME/.config/fish/config.fish"
else
  PROFILE="$HOME/.profile"
fi

if ! grep -q 'export PATH="$HOME/.ktorite' "$PROFILE"; then
  echo 'export PATH="$HOME/.ktorite:$PATH"' >>"$PROFILE"
fi

echo "Ktorite CLI installed successfully"
echo "Run 'ktorite --help' to get started."
