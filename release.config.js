const releaseRules = [
  { type: "build", release: "patch" },
  { type: "chore", release: "patch" },
  { type: "perf", release: "patch" },
  { type: "refactor", release: "patch" },
  { type: "style", release: "patch" },
];

const releseNotesTypes = [
  // { type: "feat", section: "Features" },
  // { type: "fix", section: "Bug Fixes" },
  { type: "build", section: "Other Changes", hidden: false },
  { type: "ci", section: "Other Changes", hidden: false },
  { type: "chore", section: "Other Changes", hidden: false },
  { type: "docs", section: "Other Changes", hidden: false },
  { type: "perf", section: "Other Changes" },
  { type: "refactor", section: "Other Changes", hidden: false },
  { type: "style", section: "Other Changes", hidden: false },
];

const assets = [
  "CHANGELOG.md",
  "package.json",
  "package-lock.json",
];

module.exports = {
  branches: ["main"],
  plugins: [
    [
      "@semantic-release/commit-analyzer",
      {
        preset: "conventionalcommits", // using the conventionalcommits preset, default is angular
        releaseRules,
      },
    ],
    [
      "@semantic-release/changelog",
      {
        changelogFile: "CHANGELOG.md",
      },
    ],
    [
      "@semantic-release/release-notes-generator",
      {
        preset: "conventionalcommits",
        presetConfig: {
          types: releseNotesTypes,
        },
      },
    ],
    "@semantic-release/npm",
    [
      "@semantic-release/git",
      {
        assets,
        message:
          "chore(release): :rocket: ${nextRelease.version} [skip ci]\n\n${nextRelease.notes}",
      },
    ],
    "@semantic-release/github", // test github release
  ],
};
