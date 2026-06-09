#!/usr/bin/env sh

set -e

if ! command -v java >/dev/null 2>&1; then
  echo "Error: Java is required but not found."
  echo "Install Java 17+ from https://adoptium.net or your package manager."
  exit 1
fi

if ! command -v curl >/dev/null 2>&1; then
  echo "Error: curl is required but not found."
  exit 1
fi

INSTALL_DIR="$HOME/.ktorite"
JAR_URL="https://github.com/ktorite/ktorite-cli/releases/download/v1.0.0/ktorite-all.jar"

mkdir -p "$INSTALL_DIR"
curl -L -o "$INSTALL_DIR/ktorite.jar" "$JAR_URL"

cat >"$INSTALL_DIR/ktorite" <<EOF
#!/usr/bin/env sh
exec java -jar "$INSTALL_DIR/ktorite.jar" "\$@"
EOF

chmod +x "$INSTALL_DIR/ktorite"

PARENT=$(basename "$(cat /proc/$PPID/comm 2>/dev/null || echo "sh")" 2>/dev/null)

case "$PARENT" in
fish)
  PROFILE="$HOME/.config/fish/config.fish"
  LINE='fish_add_path "$HOME/.ktorite"'
  ;;
zsh)
  PROFILE="$HOME/.zshrc"
  LINE='export PATH="$HOME/.ktorite:$PATH"'
  ;;
bash)
  PROFILE="$HOME/.bashrc"
  LINE='export PATH="$HOME/.ktorite:$PATH"'
  ;;
*)
  PROFILE="$HOME/.profile"
  LINE='export PATH="$HOME/.ktorite:$PATH"'
  ;;
esac

export PATH="$INSTALL_DIR:$PATH"

if ! grep -q "\.ktorite" "$PROFILE" 2>/dev/null; then
  echo "$LINE" >>"$PROFILE"
fi

echo "Ktorite CLI installed successfully"
echo ""
echo "To use it now:"
echo "  source $PROFILE"
echo ""
echo "Or start a new terminal, then:"
echo "  ktorite --help"
