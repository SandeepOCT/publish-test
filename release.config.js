module.exports = {
  branches: ["main"],
  plugins: [
    [
      "@semantic-release/commit-analyzer",
      {
        preset: "conventionalcommits", // using the conventionalcommits preset, default is angular
      },
    ],
    [
      "@semantic-release/changelog",
      {
        changelogFile: "CHANGELOG.md",
      },
    ],
    "@semantic-release/release-notes-generator",
    [
      "@semantic-release/git",
      {
        assets: ["CHANGELOG.md", "package.json", "package-lock.json"],
        message:
          "chore(release): :rocket: ${nextRelease.version} [skip ci]\n\n${nextRelease.notes}",
      },
    ],
    "@semantic-release/github", // test github release
  ],
};
