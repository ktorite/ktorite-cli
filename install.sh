#!/usr/bin/env sh

set -e

INSTALL_DIR="$HOME/.ktorite"
JAR_URL="https://github.com/ktorite/ktorite-cli/releases/latest/download/ktorite-all.jar"

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
  FISH=true
else
  PROFILE="$HOME/.profile"
fi

if [ -z "$FISH_CONFIGURED" ]; then
  if ! grep -q 'export PATH="$HOME/.ktorite' "$PROFILE"; then
    echo 'export PATH="$HOME/.ktorite:$PATH"' >>"$PROFILE"
  fi
else
  if ! grep -q 'set -U fish_user_paths' "$PROFILE"; then
    echo 'set -U fish_user_paths "$HOME/.ktorite" $fish_user_paths' >>"$PROFILE"
  fi
fi

echo "Ktorite CLI installed successfully"
echo "Run 'ktorite --help' to get started."
